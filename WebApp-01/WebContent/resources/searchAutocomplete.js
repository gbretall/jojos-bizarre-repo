/**
 * 
 */



function myBadLoadFunction(XMLHttpRequest,errorMessage,errorThrown) {
	alert("Load failed:"+errorMessage+" : "+errorThrown);
}



$(document).ready(function() {

	$("#tags").tooltip({
		 content: function()
        {
            return "<input id='age' title=We ask for your age only for statistical purposes.'>";
        },
	});

	$("#movie").autocomplete({
		source:
			function(request, response){
				$.ajax({
				url: 'ReturnQuery',
				dataType: "json",
				data: {"testTitle": request.term},
				success: function( data ) {
	            	var movie = [];
	            	$.each(data.movie, function(index, element){
						movie.push({label: element.movieTitle, desc: "year: "+ element.year 
						+ "\nDirector: " + element.director
						+"\nStaring: "+ element.actors
						+"\nGenre: "+ element.genre,
						 value: element.movieId});
					});
					response(movie);
	          		},
				error: myBadLoadFunction
			  });
			},
		minLength: 3,
		focus: function( event, ui ) {
			
			var selectedObj = ui.item;
			console.log (selectedObj.value);
			$("#movie").val(selectedObj.label);
			$("#hidden-movie-id").val(selectedObj.value);

			$(".ui-autocomplete").tooltip({ items: "a",
        		content: function() {
            		return "...some super extra tooltip stuff";   
        		}
    		});
    		$(".ui-autocomplete > li").attr("title", ui.item.desc);
      },
      select: function(event, ui) {
			var selectedObj = ui.item;
			console.log (selectedObj.value);
			$("#movie").val(selectedObj.label);
			$("#hidden-movie-id").val(selectedObj.value);
		}
	});

});