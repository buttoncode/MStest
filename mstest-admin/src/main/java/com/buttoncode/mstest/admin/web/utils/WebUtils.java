package com.buttoncode.mstest.admin.web.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils
{
	public static final String ADDRESS_CONNECT_TO_SERVER = "smb://192.168.1.2/intranet_files/";
	public static final String DOMAIN_NAME_CONNECT_TO_SERVER = "";
	public static final String USER_NAME_CONNECT_TO_SERVER = "uzytkownik";
	public static final String PASSWORD_NAME_CONNECT_TO_SERVER = "haslo";

	public static final String FOLDER_NAME_FILES_ACTIVE = "active_files/";
	public static final String FOLDER_NAME_FILES_SAFE = "safe_files/";
	public static final String FOLDER_NAME_FILES_DELETE = "deleted_files/";

	public static final String FILE_PDF_EMPLOYEE_NEW_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_SAFE + "employee/";
	public static final String FILE_PDF_EMPLOYEE_UPLOAD_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_SAFE + "employee_update/";

	public static final String FILE_PDF_TASKBOOK_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_ACTIVE + "task_book/";
	public static final String FILE_PDF_TASKBOOK_DELETED_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_DELETE + "task_book/";

	public static final String FILE_PDF_COMPLIANCE_ANTICORRUPTION_POLICY_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_ACTIVE + "anticorruption_policy/";
	public static final String FILE_PDF_COMPLIANCE_ANTICORRUPTION_POLICY_DELETED_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_DELETE + "anticorruption_policy/";

	public static final String IMAGES_COMPLIANCE_ANTICORRUPTION_POLICY = "_ComplianceAnticorruptionPolicy_";
	public static final String IMAGES_COMPLIANCE_CODE_OF_ETHIC = "_ComplianceCodeofEthic_";

	public static final String FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_ACTIVE + "code_of_ethic/";
	public static final String FILE_PDF_COMPLIANCE_CODE_OF_ETHIC_DELETED_URL = ADDRESS_CONNECT_TO_SERVER + FOLDER_NAME_FILES_DELETE + "code_of_ethic/";


	public static final String IMAGES_TASKBOOK = "_TaskBook_";

	public static String getURLWithContextPath(HttpServletRequest request)
	{
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
}
