package com.dajava.storage.s3.pageCapture;

public interface FileCleanupService {

	void deleteFile(String fileName);

	void deleteNonLinkedFile();
}
