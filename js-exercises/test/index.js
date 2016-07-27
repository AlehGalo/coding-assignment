'use strict';
var DiceExpression = require('../dice_expression.js');
var assert = require('assert')

exports['test that logs all failures'] = function(assert) {
    var dicea = new DiceExpression("2d5");
    console.log(dicea.min())
    assert.equal(dicea.min(), -100, 'assert failure is logged');
    assert.equal(3 + 2, 5, 'assert pass is logged');
    assert.equal(dicea.expression(), 5, 'assert pass is logged');
}

if (module == require.main) require('test').run(exports)