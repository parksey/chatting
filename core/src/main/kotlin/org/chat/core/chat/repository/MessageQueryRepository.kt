package org.chat.core.chat.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.chat.core.chat.entity.QMessageEntity
import org.chat.core.chat.entity.QMessageEntity.messageEntity
import org.chat.core.chat.vo.MessageInfo
import org.chat.core.user.entity.QUserEntity
import org.chat.core.user.entity.QUserEntity.userEntity
import org.chat.core.util.generate
import org.springframework.stereotype.Repository

@Repository
class MessageQueryRepository(
    private val queryFactory: JPAQueryFactory
) : MessageCustomRepository {

    override fun findMessagesBy(roomId: Long, cursorId: Long?, limit: Long): List<MessageInfo> {
        return queryFactory.select(Projections.constructor(
            MessageInfo::class.java,
            messageEntity.id,
            messageEntity.content,
            messageEntity.messageType,
            messageEntity.isDeleted,
            messageEntity.createdAt,
            userEntity.id,
            userEntity.name,
            userEntity.thumbnail,
        ))
            .from(messageEntity)
            .join(userEntity)
            .on(messageEntity.senderId.eq(userEntity.id))
            .where(
                messageEntity.roomId.eq(roomId),
                generate(cursorId, messageEntity.id::lt)
            )
            .orderBy(messageEntity.id.desc())
            .limit(limit)
            .fetch()
    }
}