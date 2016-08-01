"use strict";

var random = require("random-js")(); // uses the nativeMath engine

/**
 * Function used for Dice Expression.
 *
 * Definition of a Dice Expression should be as follows
 *
 * DiceExpression => Integer
 * DiceExpression => x?(d|D)y where x is the # of dice and y the sides.
 * DiceExpression => x?(d|D)% where x is the # of dice and '%' = 100
 * DiceExpression => DiceExpression +/- DiceExpression
 *
 * @param input expression for the dice.
 * @constructor of the function
 */
function DiceExpression(input) {

    /**
     * Storage for input expression.
     */
    var inputMembers = input;

    /**
     * Dices array. All dices are stored in separate place.
     * @type {Array}
     */
    var dices = [];

    /**
     * Regular expression for complex dice expression
     * @type {RegExp}
     */
    var regexOfExpression = /^((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))+$/g;

    /**
     * Regular expression for separate dices
     * @type {RegExp}
     */
    var regexInner = /((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))/g;

    initDiceMembers();

    /**
     * Definition of a Dice Member should be as follows
     *
     * DiceExpression => Integer
     * DiceExpression => x?(d|D)y where x is the # of dice and y the sides.
     * DiceExpression => x?(d|D)% where x is the # of dice and '%' = 100
     *
     * @param expression dice member definition
     * @constructor for the member
     */
    function DiceMember(expression) {

        /**
         * Dice member expression.
         */
        var memberExpression = expression;

        /**
         * Flag for constant identification.
         *
         * @type {boolean}
         */
        var isConstant = false;

        /**
         * Flag for matching positive or negative member.
         */
        var negative = expression.startsWith("-");

        /**
         * First number in dice member. It can be empty.
         *
         * @type {number}
         */
        var startNumber = 1;

        /**
         * Second number in dice member. It can't be empty.
         *
         * @type {number}
         */
        var endNumber = 1;

        initDigits(getSplitChar(memberExpression));

        /**
         * Roll the dice.
         *
         * @returns {*}
         */
        this.roll = function () {
            if (isConstant) {
                return endNumber;
            }
            var rollSum = 0;
            for (var i = 0; i < startNumber; i++) {
                rollSum += random.integer(1, endNumber)
            }
            return applyNegative(rollSum);
        };

        /**
         * Mininum dice value.
         *
         * @returns {number}
         */
        this.min = function () {
            if (isConstant) {
                return endNumber;
            }
            return Math.min(applyNegative(startNumber), applyNegative(startNumber * endNumber));
        };


        /**
         * Maximum dice value.
         *
         * @returns {number}
         */
        this.max = function () {
            if (isConstant) {
                return endNumber;
            }
            return Math.max(applyNegative(startNumber * endNumber), applyNegative(startNumber));
        };

        /**
         * Roll the dice with keeping the details.
         *
         * @returns {*}
         */
        this.rollDetailed = function () {
            if (isConstant) {
                return {roll: endNumber, dice: [endNumber]};
            } else {
                var rollSum = 0, all = [];
                for (var i = 0; i < startNumber; i++) {
                    var value = random.integer(1, endNumber);
                    rollSum += value;
                    all.push(value);
                }
                return {roll: applyNegative(rollSum), dice: all};
            }
        };

        /**
         *
         * Expression of the member.
         *
         * @returns {*}
         */
        this.getDice = function () {
            return memberExpression;
        };

        /**
         * Local function for returning number and -number depending of negative flag.
         *
         * @param num negative or positive depending of the flag.
         * @returns {number}
         */
        function applyNegative(num) {
            return negative ? -num : num;
        }

        /**
         * Local function for splitting expression by split character.
         *
         * @param splitChar any character that splits the expression.
         * @returns {*[]}
         */
        function getNumbers(splitChar) {
            var numbers = splitNumbers(splitChar);
            var number1 = parseInt(numbers[0]);
            if (isNaN(number1)) {
                number1 = 1;
            }
            var number2 = parseInt(numbers[1]);
            if (isNaN(number2)) {
                number2 = 100;
            }
            return [number1, number2];
        }

        /**
         * Local function that splits numbers based on split letter.
         *
         * @param splitLetter any letter.
         * @returns {Array}
         */
        function splitNumbers(splitLetter) {
            if (memberExpression.includes(splitLetter)) {
                return memberExpression.split(splitLetter);
            }
            return [];
        }

        /**
         *
         * Local function for getting split character that is d or D.
         *
         * @param expr to be validated.
         * @returns {*}
         */
        function getSplitChar(expr) {
            var splitChar = /(d|D)/g.exec(expr);
            if (splitChar) {
                return splitChar[1];
            }
            return null;
        }

        /**
         *
         * Local function for initing local data structures.
         *
         * @param splitChar that identifies if is the member a regex or a constant.
         */
        function initDigits(splitChar) {
            if (splitChar) {
                var numbers = getNumbers(splitChar);
                startNumber = Math.abs(numbers[0]);
                endNumber = numbers[1];
            } else {
                isConstant = true;
                startNumber = 1;
                endNumber = parseInt(expression);
            }
        }
    }

    /**
     * Minimum value of a dice expression.
     *
     * @returns {*}
     */
    this.min = function () {
        return callDiceMemberFunction(function (diceMember) {
            return diceMember.min();
        });
    };

    /**
     * Maximum value of a dice expression.
     *
     * @returns {*}
     */
    this.max = function () {
        return callDiceMemberFunction(function (diceMember) {
            return diceMember.max();
        });
    };

    /**
     * Roll the dice expression.
     *
     * @returns {*}
     */
    this.roll = function () {
        return callDiceMemberFunction(function (diceMember) {
            return diceMember.roll();
        });
    };

    /**
     * Roll the dice expression containing details.
     *
     * @returns {{roll: number, dice: Array}}
     */
    this.rollDetailed = function () {
        var res = new Array(dices.length);
        var sum = 0;
        var counter = 0;
        dices.forEach(function (diceMember) {
            var rollDetailed = diceMember.rollDetailed();
            res[counter] = rollDetailed["dice"];
            sum += parseInt(rollDetailed["roll"]);
            ++counter;
        });
        return {roll: sum, dice: res};
    };

    /**
     * Roll the dice expression containing details.
     *
     * @returns {{roll: number, dice: Array}}
     */
    this.rollDetailedAsString = function () {
        return JSON.stringify(this.rollDetailed());
    };
    
    /**
     * Dice expression itself.
     *
     * @returns {*}
     */
    this.expression = function () {
        return inputMembers;
    };

    /**
     *
     * Local function for validating and initing the values of dice members.
     *
     */
    function initDiceMembers() {
        if (regexOfExpression.test(inputMembers)) {
            var matches;
            while (matches = regexInner.exec(inputMembers)) {
                dices.push(new DiceMember(matches[1]));
            }
        } else {
            throw new Error("Incorrect Dice Expression");
        }
    }

    /**
     *
     * Local function for calling a function for all dices and keeping the sum of it.
     *
     * @param funcOfMember function for processing the member.
     * @returns {*}
     */
    function callDiceMemberFunction(funcOfMember) {
        return dices.reduce(function (sum, diceMember) {
            return sum += parseInt(funcOfMember(diceMember));
        }, 0);
    }
}

module.exports = DiceExpression;