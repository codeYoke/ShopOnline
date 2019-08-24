//异步调用的封装
//为了应对所有的现代浏览器，包括 IE5 和 IE6，请检查浏览器是否支持 XMLHttpRequest 对象。
//如果支持，则创建 XMLHttpRequest 对象。
//如果不支持，则创建 ActiveXObject ,老版本的 Internet Explorer （IE5 和 IE6）使用 ActiveX 对象：
/**
 * @param {Object} method get/post
 * @param {Object} url 请求的地址
 * @param {Object} data 请求参数
 * @param {Object} callback 回调函数
 * @param {Object} json 是否要处理json
 */
function ajax(method,url,data,callback,json){
	//1.创建XMLHttpRequest对象
	console.log("进入ajax处理！")
	var xmlhttp = null;
	if(window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xmlhttp = new ActiveXObject();
	}
	
	//2.创建回调函数，在回调函数中处理返回值
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			//获取服务器的响应，获得字符串形式的响应数据，也可以获取responseXml形式的数据
			console.log("正在处理数据！")
			var result = xmlhttp.responseText;
			if(json !=undefined && json.toUpperCase() == "JSON"){
				var objJson = null;
				//如果是ie
				if(window.ActiveXObject){
					objJson = eval('('+ result +')')
				}else{
					objJson = JSON.parse(result);
				}
				callback(objJson);
			}else{
				callback(result);
			}
		}else{
			console.log('error!')
		}
	}
	
	//3.发送请求（判断是get还是post请求）
	if(method.toUpperCase() == "GET"){
		//为了避免缓存的结果情况，请向 URL 添加一个唯一的 ID：
		//xmlhttp.open("GET",url+"t=" + Math.random(),true);
		
		//希望通过 GET 方法发送信息，请向 URL 添加信息：
		//xmlhttp.open("GET","demo_get2.asp?fname=Bill&lname=Gates",true);
		xmlhttp.open("GET",url,true);

		xmlhttp.send();
	}else{
		//如果有 HTML 表单那样 POST 数据，
		//请使用 setRequestHeader() 来添加 HTTP 头。
		//然后在 send() 方法中规定您希望发送的数据：
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(data);
	}
	
}