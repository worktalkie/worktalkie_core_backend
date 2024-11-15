package com.worktalkie.src.global;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Column(name = "deleted_at", nullable = true)
	private Timestamp deletedAt;

	public void softDelete() {
		this.deletedAt = Timestamp.valueOf(LocalDateTime.now());
	}
}