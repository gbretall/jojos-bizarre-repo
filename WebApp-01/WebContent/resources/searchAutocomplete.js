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
							movie.push({label: element.movieTitle, desc: "year: "+ element.year + "\nDirector: " + element.director,
							 value: element.movieId});
						});
					response(movie);
	          		},
				error: myBadLoadFunction
			  });
			},
		minLength: 3,
		focus: function( event, ui ) {
			//console.log($(".ui-autocomplete"));
			//console.log(ui.item.desc)
			$(".ui-autocomplete").tooltip({ items: "a",
        		content: function() {
            		return "...some super extra tooltip stuff";   
        		}
    		});
    		$(".ui-autocomplete > li").attr("title", ui.item.desc);
      }
	});

});