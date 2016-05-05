<?php
require 'mysql_connect.php';
switch($_REQUEST['table'])
{
case "door":
$sql="select * from door";
break;
case "log":
$sql="select * from log";
break;
case "qrcode":
$sql="select * from qrcode";
break;
case "relationship":
$sql="select * from relationship";
break;
case "user":
$sql="select * from user";
break;
default:
die('没有输入参数');
}

$stmt=$mysqli->stmt_init();
$stmt=$mysqli->prepare($sql);
//$stmt->bind_param("s",$seek);
$stmt->execute();
$result=$stmt->get_result();
 
$i=0;
while($row=mysqli_fetch_array($result, MYSQLI_ASSOC)){
 
$users[$i]=$row;
$i++;
}
echo json_encode(array('dataList'=>$users));

  
mysqli_free_result($result);  
//ÊÍ·Å½á¹û  

?>