package com.dajava.api.domain.image.service.pageCapture;

public interface FileCleanupService {

	void deleteFile(String fileName);

	void deleteNonLinkedFile();
}
