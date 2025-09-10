package com.dajava.persistence.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dajava.api.domain.register.entity.PageCaptureData;

public interface PageCaptureDataRepository extends JpaRepository<PageCaptureData, Long> {

	PageCaptureData findByPageUrl(String pageCaptureUrl);
}
