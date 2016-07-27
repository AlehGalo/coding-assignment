"use strict";

var random = require("random-js")(); // uses the nativeMath engine

function DiceExpression(paramter) {
    var value = random.integer(0, 99);
    this.diceExpression = paramter;
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

module.exports = DiceExpression;
