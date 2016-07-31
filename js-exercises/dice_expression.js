"use strict";

var random = require("random-js")(); // uses the nativeMath engine

function DiceExpression(input) {
    
    var inputMembers = input;
    var dices = [];
    /**
     * Regular expression for complex dise expression and separate dices
     * @type {RegExp}
     */
    
    var regex =/^((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))+$/g;
    var regexInner = /((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))/g;

    initDiceMembers();


    function initDiceMembers(){
        if(regex.test(inputMembers)){
            var matches;
            while (matches = regexInner.exec(inputMembers)) {
                dices.push(new DiceMember(matches[1]));
            }
        } else {
            throw new Error("Incorrect Dice Expression");
        }
    }

    function DiceMember(expression){
        var memberExpression = expression;
        var isConstant = false;
        var negative = expression.startsWith("-");
        var startNumber = 1;
        var endNumber = 1;

        initDigits(getSplitChar(memberExpression));

        this.roll = function(){
            if(isConstant){
                return endNumber;
            }
            var rollSum = 0;
            for (var i = 0; i < startNumber; i++) {
                rollSum += random.integer(1, endNumber)
            }
            return applyNegative(rollSum);
        };

        this.min = function(){
            if(isConstant){
                return endNumber;
            }
            return Math.min(applyNegative(startNumber), applyNegative(startNumber*endNumber));
        };


        this.max = function(){
            if(isConstant){
                return endNumber;
            }
            return Math.max(applyNegative(startNumber*endNumber), applyNegative(startNumber));
        };

        this.rollDetailed = function(){
            if(isConstant){
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

        this.getDice = function(){
            return memberExpression;
        };


        function applyNegative(num){
            return negative ? -num : num;
        }
        
        function getNumbers(splitChar){
            var numbers = splitNumbers(splitChar);
            var number1 = parseInt(numbers[0]);
            if(isNaN(number1)){
                number1 = 1;
            }
            var number2 = parseInt(numbers[1]);
            if(isNaN(number2)){
                number2 = 100;
            }
            return [number1, number2];
        }

        function splitNumbers(splitLetter){
            if(memberExpression.includes(splitLetter)){
                return memberExpression.split(splitLetter);
            }
            return [];
        }

        function getSplitChar(expr){
            var splitChar = /(d|D)/g.exec(expr);
            if(splitChar){
                return splitChar[1];
            }
            return null;
        }
        
        function initDigits(splitChar){
            if (splitChar){
                var numbers = getNumbers(splitChar);
                startNumber = Math.abs(numbers[0]);
                endNumber = numbers[1];
            }else {
                isConstant = true;
                startNumber = 1;
                endNumber = parseInt(expression);
            }
        }
    }

    this.min = function(){
        function callMin(sum, diceMember) {
            return sum += parseInt(diceMember.min());
        }
        return dices.reduce(callMin, 0);
    };

    this.max = function () {
        function callMax(sum, diceMember){
            return sum += parseInt(diceMember.max());
        }
        return dices.reduce(callMax, 0);
    };

    this.expression = function (){
        return inputMembers;
    };

    this.roll = function(){
        function sumOfDice(sum, diceMember){
            return sum + parseInt(diceMember.roll());
        }
        return dices.reduce(sumOfDice, 0);
    };

    this.rollDetailed = function(){
        var res = [];
        var sum = 0;
        dices.forEach(function(diceMember){
            var rollDetailed = diceMember.rollDetailed();
            res.push(rollDetailed["dice"]);
            sum += parseInt(rollDetailed["roll"]);
        });
        return {roll: sum, dice: res};
    };    
    
}


module.exports = DiceExpression;
