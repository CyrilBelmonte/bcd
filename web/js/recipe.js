/**
 * -------------------------------------------------------
 *   Cook with ease | Big Cooking Data
 * -------------------------------------------------------
 *
 * Authors:
 *   Valentin BELYN
 *   Cyril BELMONTE
 *   Vincent ARCHAMBAULT
 *   Loïc TRAMIS
 *
 * --
 *   Master 1 IISC 2018-2019
 *   University of Cergy-Pontoise
 */

$(document).ready(function() {
  var emotions = ['angry', 'disappointed', 'meh', 'happy', 'inlove'];
  var ratingInput = $('#rating-input');

  $('#recipe-rating').emotionsRating({
    emotions: emotions,
    emotionSize: 24,
    initialRating: 3,
    onUpdate: function(value) {
      ratingInput.val(value);
    }
  });
});
