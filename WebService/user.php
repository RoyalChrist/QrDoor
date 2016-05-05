<?php
/**
 * Created by PhpStorm.
 * User: 95155
 * Date: 2016/4/10
 * Time: 20:19
 */
header("Content-Type: text/html;charset=utf-8");
require "encode.php";
$con = mysqli_connect("localhost", "root", "roots704", "door");

$loginName = $_GET['loginName'];
$password = $_GET['password'];

$sql = "SELECT * from user WHERE loginName='$loginName' AND password='$password'";
$result = mysqli_query($con, $sql);
$results = array();
while ($row = mysqlI_fetch_assoc($result)) {
    $results[] = $row;
}

// 将数组转成json格式
echo JSON($results);

mysqli_close($con);