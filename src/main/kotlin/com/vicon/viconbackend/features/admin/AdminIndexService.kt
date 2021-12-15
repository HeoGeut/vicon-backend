package com.vicon.viconbackend.features.admin

import org.springframework.stereotype.Service

@Service
class AdminIndexService {
    fun selectedNavigation(requestURI: String): String {
        return when (requestURI) {
            "/admin" -> "관리자 메인"
            "/admin/members" -> "회원 관리"
            "/admin/contests" -> "콘테스트 관리"
            "/admin/video" -> "비디오 콘테스트 사례"
            "/admin/creator" -> "크리에이터 관리"
            "/admin/review" -> "후기 관리"
            "/admin/event" -> "이벤트 관리"
            "/admin/qna" -> "문의 관리"
            "/admin/chat" -> "소통 관리"
            "/admin/faq" -> "자주묻는 질문"
            else -> ""
        }
    }
}