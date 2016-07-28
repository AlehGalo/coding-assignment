'use strict';
var DiceExpression = require('../dice_expression.js');
var assert = require('assert')

exports['test that logs all failures'] = function(assert) {
    var dicea = new DiceExpression("2d5");
    console.log(dicea.min());
    assert.equal(dicea.min(), -100, 'assert failure is logged');
    assert.equal(3 + 2, 5, 'assert pass is logged');
    assert.equal(dicea.expression(), 5, 'assert pass is logged');
};


exports['test input parameters validation'] = function(assert) {
    checkCorrectArray(["2d5", "D%", "d34567890", "87", "-54635", "+67", "3d%", "8d6+4-8-d%"]);

    checkIncorrectArray(["d", "D", "gsdgsdf", "01", "0000000", "_", "0d0d","6d4+", ""]);

    function checkCorrectArray(array){
        array.forEach(checkCorrect);
    }

    function checkIncorrectArray(array){
        array.forEach(checkIncorrect);
    }
    
    function checkCorrect(expression){
        assertExpression(expression, true);
    }

    function checkIncorrect(expression){
        assertExpression(expression, false);
    }

    function assertExpression(expression, result){
        assert.equal(new DiceExpression(expression).validate(), result, 'Dice Expression test ' + expression +"\'");
    }
};

if (module == require.main) require('test').run(exports)