<?php
/**
 * Created by PhpStorm.
 * User: 95155
 * Date: 2016/4/9
 * Time: 13:48
 */
header("Content-Type: text/html;charset=utf-8");
$con = mysqli_connect("localhost", "root", "roots704", "door");
$loginName = $_GET['loginName'];
$password = $_GET['password'];

$sql = "SELECT * from user WHERE loginName='$loginName' AND password='$password'";
$result = mysqli_query($con,$sql);

if (mysqli_num_rows($result) != 0) {

    $response["login"] = 1;
} else {

    $response["login"] = 0;
}
echo json_encode($response);
mysqli_close($con);