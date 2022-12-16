package com.example.community

import com.example.model.Community

interface CommunityActionHandler {
    fun onCommunityItemClicked(community: Community)
}