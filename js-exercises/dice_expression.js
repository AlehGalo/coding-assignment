"use strict";

var random = require("random-js")(); // uses the nativeMath engine

function DiceMember(expression){
    this.expression = expression;

    this.isConstant = false;    
    this.negative = expression.startsWith("-");
    
    this.startNumber = 1;
    this.endNumber = 1;


    var splitChar = getSplitChar(this.expression);

    if (splitChar){
        var numbers = getNumbers();
        this.startNumber = numbers[0];
        this.endNumber = numbers[1];
    }else {
        this.isConstant = true;
        this.startNumber = 1;
        this.endNumber = expression;
    }
    
    function getNumbers(){
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
        if(expression.includes(splitLetter)){
            return expression.split(splitLetter);
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
}

DiceMember.prototype.min = function(){
    if(this.isConstant){
        return this.endNumber;
    }
    return this.startNumber;
};

DiceMember.prototype.isnegative = function(){
    return this.negative;
};

DiceMember.prototype.max = function(){
    return this.startNumber*this.endNumber;
};

DiceMember.prototype.rollDetailed = function(){
    if(this.isConstant){
        return {roll: this.endNumber, dice: [this.endNumber]};
    } else {
        var rollSum = 0, all = [];
        for (var i = 0; i < this.startNumber; i++) {
            var value = random.integer(1, this.endNumber);
            rollSum += value;
            all.push(value);
        }
        return {roll: rollSum, dice: all};
    }
};


DiceMember.prototype.roll = function(){
    if(this.isConstant){
        return this.endNumber;
    }
        var rollSum = 0;
        for (var i = 0; i < this.startNumber; i++) {
            rollSum += random.integer(1, this.endNumber)
        }
        return rollSum;

};

function DiceExpression(input) {
    
    this.inputMembers = input;
    this.dices = [];
    /**
     * Regular expression for complex dise expression
     * @type {RegExp}
     */
    this.regex =/^((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))+$/g;
    this.regexInner = /((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))/g;

    this.valid = this.regex.test(input);

    if(this.valid){
        var matches;
        while (matches = this.regexInner.exec(input)) {
            this.dices.push(new DiceMember(matches[1]));
        }
    }

    // this.diceExpression = paramter;
}

DiceExpression.prototype.min = function(){
    var sum = 0;

    this.dices.forEach(function callRoll(diceMember){
        var value = parseInt(diceMember.min());
        sum += value;//diceMember.isnegative() ? -value: value;
    });
    return sum;
};

DiceExpression.prototype.max = function () {
    var sum = 0;

    this.dices.forEach(function callRoll(diceMember){
        sum += parseInt(diceMember.max());//diceMember.isnegative() ? -diceMember.max(): diceMember.max();
    });
    return sum;
};

DiceExpression.prototype.expression = function (){
    return this.inputMembers;
};

DiceExpression.prototype.validate = function(){
    return this.valid;
};

DiceExpression.prototype.roll = function(){
    var sum = 0;
    
    this.dices.forEach(function(diceMember){
        sum += parseInt(diceMember.roll());
    });
    return sum;
};

DiceExpression.prototype.rollDetailed = function(){
    var res = [];
    var sum = 0;

    this.dices.forEach(function(diceMember){
        var rollDetailed = diceMember.rollDetailed();
        res.push(rollDetailed["dice"]);
        sum += parseInt(rollDetailed["roll"]);
    });
    return {roll: sum, dice: res};
};

module.exports = DiceExpression;
