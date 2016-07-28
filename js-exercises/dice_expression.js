"use strict";

var random = require("random-js")(); // uses the nativeMath engine

function DiceMember(expression){
    this.expression = expression;
    this.regex =/^(\+|-)?([1-9][0-9]*)?(?:(?:d|D)?([1-9][0-9]*)+|d%|D%)$/;
    
}

DiceMember.prototype.validate = function(){
    return this.regex.test(this.expression);
};
// DiceMember.prototype.validate = function(){
//     return this.expression.match(this.expressionValidRegex).length();
// };

function DiceExpression(input) {
    
    this.inputMembers = input;
    
    /**
     * Regular expression for complex dise expression
     * @type {RegExp}
     */
    this.regex =/^((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))+$/;
    // ((?:\+|-)?(?:[1-9][0-9]*)?(?:(?:d|D)?(?:[1-9][0-9]*)+|d%|D%))+
    this.valid = this.regex.test(input);
    
    var variables = this.inputMembers.match(this.regex);
    console.log(variables);
    if(variables) {
        console.log(" >> " + variables.length);
        variables.forEach(function () {
            debugger;
        });
    }
    // this.diceExpression = paramter;
}

DiceExpression.prototype.min = function(){
    return -100;
};

DiceExpression.prototype.max = function () {
    return 100;
};

DiceExpression.prototype.random = function (){
  return random.integer(0, 100);
};

DiceExpression.prototype.expression = function (){
    return this.diceExpression;
};

DiceExpression.prototype.validate = function(){
    return this.valid;
};


module.exports = DiceExpression;
