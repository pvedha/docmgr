<?php

// A list of permitted file extensions
$allowed = array('png', 'jpg', 'gif','zip', 'txt');

if(isset($_FILES['upl']) && $_FILES['upl']['error'] == 0){

    echo '{"status":"success"}';
    
	$extension = pathinfo($_FILES['upl']['name'], PATHINFO_EXTENSION);
    echo '{"meowowowowo":"woooof"}';
	if(!in_array(strtolower($extension), $allowed)){
		echo '{"status":"error"}';
		exit;
	}

    
        
        move_uploaded_file($_FILES['upl']['tmp_name'], 'c:/temp/'.$_FILES['upl']['name'])
        
	if(move_uploaded_file($_FILES['upl']['tmp_name'], 'uploads/'.$_FILES['upl']['name'])){
		echo '{"status":"success"}';
		exit;
	}
}

echo '{"status":"error"}';
exit;
