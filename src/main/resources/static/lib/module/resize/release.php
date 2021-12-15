# release content
# =============================================================================
# - Fatal error: Allowed memory size of ...... bytes exhausted... 에러로 인해
#   상단 ini_set('memory_limit', -1); 추가
#   위 에러는 php.ini에서 설정한 메모리의 크기보다 페이지에서 사용한 메모리가
#   더 클경우 나는 에러이며, Image Processing에서 이미지의 용량을 제한적으로
#   둔다면 주석처리하여 사용
#
# - 이미지의 Width의 값을 리턴해주기위해 
#   전역변수 $image_width 추가
#   'load' 함수에 $this->image_width = $image_info[0]; 추가
# =============================================================================
