<?php
include("conn.php");
include("function.php");

if(isset($_POST["food_name"]) && isset($_POST["food_price"]) && isset($_POST["food_image"]) && isset($_POST["merchant_id"]))
{
    echo createFood($connection, $_POST["food_name"], (int)$_POST["food_price"], $_POST["food_image"], (int)$_POST["merchant_id"]);
}
