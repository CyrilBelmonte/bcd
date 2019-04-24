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
  var searchBar = $('.search-box > input');
  var helpMessage = $('.search-help');

  searchBar.keyup(function() {
    var isEmpty = searchBar.val() == '';

    helpMessage.toggleClass('hide-message', !isEmpty);
  });
});
