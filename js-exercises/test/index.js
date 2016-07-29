'use strict';
var DiceExpression = require('../dice_expression.js');
var assert = require('assert');

var expressions = ["2d5", "d18", "d%", "5d%", "3D678","-8", "-5D2", "4D9-9+3"];
var results = [[2, 10], [1, 18], [1, 100], [5, 500], [3, 2034], [-8, -8], [-5, -10], [-2, 30]];

exports['test dice expression'] = function(assert) {
    makeTest(expressions, results);
    function makeTest(expressionArray, resultsArray){
        var counter = 0;
        expressionArray.forEach(function(item){
            var diceExpre = new DiceExpression(item);
            
            var resArray = resultsArray[counter];
            var min = resArray[0], max = resArray[1]; 
            
            assert.equal(diceExpre.min(), min, 'min value ' + min);
            assert.equal(diceExpre.max(), max, 'max value ' + max);
            ++counter;
        });
    }
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
        assert.equal(new DiceExpression(expression).validate(), result,
            'Dice Expression validation test ' + expression +"\'");
    }
};

exports['test dice roll validation'] = function(assert) {
    makeTest(expressions, results, 10);
    function makeTest(expressionArray, minMaxArray, repeatTime){
        var counter = 0;
            expressionArray.forEach(function (item) {
                var diceExp = new DiceExpression(item);
                var itemToCheck = minMaxArray[counter];
                for(var i=0; i< repeatTime; i++) {
                    var rollValue = diceExp.roll();
                    var operationsResult = rollValue >= itemToCheck[0] 
                        && rollValue <= itemToCheck[1];
                    assert.ok(operationsResult,
                        "Dice expression checked " + rollValue + " -> [" + itemToCheck[0] 
                        + "..." + itemToCheck[1] + "]");
                }
                ++counter;
            });
    }
};

if (module == require.main) require('test').run(exports);