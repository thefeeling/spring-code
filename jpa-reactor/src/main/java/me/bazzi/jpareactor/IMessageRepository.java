package me.bazzi.jpareactor;

import org.springframework.data.jpa.repository.JpaRepository;

interface IMessageRepository extends JpaRepository<Message, Long> {}
