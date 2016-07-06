<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <title>Error Page</title>
  <style type="text/css">
  	body {
  color: #666;
  text-align: center;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  margin: 0;
  width: 800px;
  margin: auto;
  font-size: 14px;
}

h1 {
  font-size: 56px;
  line-height: 100px;
  font-weight: normal;
  color: #456;
}

h2 { 
  font-size: 24px; 
  color: #666; 
  line-height: 1.5em;
}

h3 {
  color: #456;
  font-size: 20px;
  font-weight: normal;
  line-height: 28px;
}

hr {
  margin: 18px 0;
  border: 0;
  border-top: 1px solid #EEE;
  border-bottom: 1px solid white;
}
a{
  color: #456;
  text-decoration: none;
}
  </style>
</head>

<body>
  <h1>Error</h1>
  <h3>${message }</h3>
  <hr />
  	<a href="/">Back Home</a>

</body>
</html>

