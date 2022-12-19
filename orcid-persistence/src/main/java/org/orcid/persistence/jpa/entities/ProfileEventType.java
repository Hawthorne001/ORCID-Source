package org.orcid.persistence.jpa.entities;

/**
 * 
 * @author Will Simpson
 * 
 */
public enum ProfileEventType {

    CLAIM_REMINDER_SENT, 
    EMAIL_VERIFY_CROSSREF_MARKETING_FAIL, EMAIL_VERIFY_CROSSREF_MARKETING_SENT, EMAIL_VERIFY_CROSSREF_MARKETING_SKIPPED, 
    POLICY_UPDATE_2014_03_SENT, POLICY_UPDATE_2014_03_FAIL, POLICY_UPDATE_2014_03_SKIPPED,
    SERVICE_ANNOUNCEMENT_SENT_1_FOR_2015, SERVICE_ANNOUNCEMENT_FAIL_1_FOR_2015, SERVICE_ANNOUNCEMENT_SKIPPED_1_FOR_2015, 
    //Indicates that the account is deprecated
    PROFILE_DEPRECATED, 
    //Indicates that an account has been deprecated and you are the primary account
    PROFILE_DEPRECATION,
    //Indicates that the delegation process of an account was started by an admin
    ADMIN_PROFILE_DELEGATION_REQUEST,
    
    
    // Verified_Required_Announcement_2017
    VERIFIED_REQUIRED_SKIPPED_2017, VERIFIED_REQUIRED_HAS_VALIDATED_2017, VERIFIED_REQUIRED_SENT_2017, VERIFIED_REQUIRED_FAIL_2017,
    
    // EmailFrequencyServiceAnnouncement2018
    GDPR_EMAIL_FREQUENCY_UPDATES_2018_SKIPPED, GDPR_EMAIL_FREQUENCY_UPDATES_2018_NOTIFICATION_CREATED, 
    GDPR_EMAIL_FREQUENCY_UPDATES_2018_SENT, GDPR_EMAIL_FREQUENCY_UPDATES_2018_FAIL,
    
    // July 2018 quarterly notification
    JULY_2018_SKIPPED, JULY_2018_CREATED, JULY_2018_FAIL,
    
    // March 2019 quarterly notification
    MARCH_2019_SENT, MARCH_2019_SKIPPED, MARCH_2019_FAILED,
    
    // 2019 Visibility settings quarterly notification
    EMAIL_VIS_2019_SENT, EMAIL_VIS_2019_SKIPPED, EMAIL_VIS_2019_FAILED,

    // 2FA enable/disable events
    PROFILE_2FA_ENABLED, PROFILE_2FA_DISABLED, PROFILE_2FA_DISABLED_BY_ADMIN;
}
