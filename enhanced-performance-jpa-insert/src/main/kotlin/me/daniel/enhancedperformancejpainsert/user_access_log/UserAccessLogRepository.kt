package me.daniel.enhancedperformancejpainsert.user_access_log

import me.daniel.enhancedperformancejpainsert.support.BatchRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccessLogRepository: BatchRepository<UserAccessLog, Long>