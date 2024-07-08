package com.example.icewarpassignment.domain.interactor

import com.example.icewarpassignment.data.datasource.request.GetChannelRequest
import com.example.icewarpassignment.domain.entity.ChannelListEntity
import com.example.icewarpassignment.domain.repository.UserRepository
import io.reactivex.Observable

class GetChannelUC constructor(val userRepository: UserRepository) :
    UseCase<ChannelListEntity, GetChannelRequest>() {

    override fun build(param: GetChannelRequest): Observable<ChannelListEntity> {
        return userRepository.getChannelList(param.token, param.includeUnreadCount,
                param.excludeMembers,param.includePermissions)
    }
}