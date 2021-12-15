<?php
include $_SERVER['DOCUMENT_ROOT']."/admin/proc.php";
//header('Content-Type: application/jsonp');
$type = $_POST['type'];
$key = $_POST['key'];

switch ($type)
{
    
    /* s:Data update */
    // 재무관리 > 기초자료 > 거래처관리 : 사용구분
    case "am_state_change":
        $key = decode($key);
        $val = $_POST['val'];
        
        $sql = "UPDATE am SET am_state = '{$val}' WHERE am_idx = '{$key}'";
        $res = $db->query($sql);
        
        if ($res)
        {
            echo true;
        }
        else 
        {
            echo false;    
        }
        
        break;
        
    /* e:Data update */
    
    
    /* s:Get Data */
    case "wrt_oa_idx":
        $sql = "SELECT * FROM oaccounts WHERE oa_state = 'A'";
        $res = $db->get_results($sql);
        if ($res)
        {
            $arr = array();
            $i = 0;
            foreach ($res as $data)
            {
                $arr[$i++] = array(
                    "key" => "{$data->oa_idx}",
                    "name" => "({$data->oa_code}){$data->oa_name}"
                );
            }
            
            echo json_encode($arr);
        }
        else
        {
            echo false;
        }
        break;
        
    case "wrt_ass1_idx":
        $sql = "SELECT * FROM ass1 WHERE ass1_state = 'A'";
        $res = $db->get_results($sql);
        if ($res)
        {
            $arr = array();
            $i = 0;
            foreach ($res as $data)
            {
                $arr[$i++] = array(
                    "key" => "{$data->ass1_idx}",
                    "name" => "{$data->ass1_name}"
                );
            }
            
            echo json_encode($arr);
        }
        else
        {
            echo false;
        }
        break;
        
    case "ass1_change":
        $sql = "SELECT * FROM ass2 WHERE ass2_state = 'A' AND ass1_idx = '{$key}' ";
        $res = $db->get_results($sql);
        if ($res)
        {
            $arr = array();
            $i = 0;
            foreach ($res as $data)
            {
                $arr[$i++] = array(
                    "key" => "{$data->ass2_idx}",
                    "name" => "{$data->ass2_name}"
                );
            }
            
            echo json_encode($arr);
        }
        else
        {
            echo json_encode(array("key"=>"0", "name"=>""));
        }
        break;
        
    case "ass2_change":
        $sql = "SELECT * FROM ass3 WHERE ass3_state = 'A' AND ass2_idx = '{$key}' ";
        $res = $db->get_results($sql);
        if ($res)
        {
            $arr = array();
            $i = 0;
            foreach ($res as $data)
            {
                $arr[$i++] = array(
                    "key" => "{$data->ass3_idx}",
                    "name" => "{$data->ass3_name}"
                );
            }
            
            echo json_encode($arr);
        }
        else
        {
            echo json_encode(array("key"=>"0", "name"=>""));
        }
        break;
        
    /* e:Get Data */
    
    
    /* s:Data Delete Chceck */
    case 'am_del_chk':
        $key = decode($key);
        $sql = "select count(*) as cnt from om where om_code = (select am_ac from am where am_idx = '{$key}')";
        $cnt = $db->get_row($sql)->cnt;
        if ($cnt>0)
        {
            echo "삭제하시려는 데이터는\n일계자료에서 [{$cnt}]건 사용된 자료입니다.\n";
        }
        else
        {
            echo "";
        }
        
        
        break;
    /* e:Data Delete Chceck */
}
?>