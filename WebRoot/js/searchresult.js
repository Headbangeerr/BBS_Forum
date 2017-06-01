/**
 * 
 */
function paging(t){
	var page=$(t).attr("name");
	var searchFlag=$("#searchFlag").val();
	var keyword=$("#keyword").val();
	var username=$("#username").val();
	var search_keyword1=$("#search_keyword1").val();
	var search_username=$("#search_username").val();
	var search_keyword2=$("#search_keyword2").val();
	var search_childboardlist=$("#search_childboardlist").val();
	var url="search?page="+page+"&searchFlag="+searchFlag;
	switch (searchFlag) {
	case ("1"):
		url+="&keyword="+keyword;
		
		break;
	case ("2"):
		url+="&username="+username;
		
		break;
	case ("3"):
		url+="&search_childboardlist="+search_childboardlist+"&search_keyword1="+search_keyword1;
		
		break;
	case ("4"):
		url+="&search_username="+search_username+"&search_keyword2="+search_keyword2;
		
		break;
	}
	window.location.href = url;
}