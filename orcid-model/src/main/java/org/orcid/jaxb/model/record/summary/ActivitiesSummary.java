/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.01.18 at 10:32:14 AM GMT 
//

package org.orcid.jaxb.model.record.summary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.orcid.jaxb.model.record.ActivitiesContainer;
import org.orcid.jaxb.model.record.Activity;
import org.orcid.jaxb.model.record.RecordUtil;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "educations", "employments", "fundings", "peerReviews", "works" })
@XmlRootElement(name = "activities-summary", namespace = "http://www.orcid.org/ns/activities")
public class ActivitiesSummary implements Serializable, ActivitiesContainer {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "educations", namespace = "http://www.orcid.org/ns/activities")
    protected Educations educations;
    @XmlElement(name = "employments", namespace = "http://www.orcid.org/ns/activities")
    protected Employments employments;
    @XmlElement(name = "fundings", namespace = "http://www.orcid.org/ns/activities")
    protected Fundings fundings;
    @XmlElement(name = "peer-reviews", namespace = "http://www.orcid.org/ns/activities")
    protected PeerReviews peerReviews;
    @XmlElement(name = "works", namespace = "http://www.orcid.org/ns/activities")
    protected Works works;

    public Educations getEducations() {
        return educations;
    }

    public void setEducations(Educations educations) {
        this.educations = educations;
    }

    public Employments getEmployments() {
        return employments;
    }

    public void setEmployments(Employments employments) {
        this.employments = employments;
    }

    public PeerReviews getPeerReviews() {
        if(peerReviews == null)
            peerReviews = new PeerReviews();
        return peerReviews;
    }

    public void setPeerReviews(PeerReviews peerReviews) {
        this.peerReviews = peerReviews;
    }

    public Works getWorks() {
        if (works == null)
            works = new Works();
        return works;
    }

    public void setWorks(Works works) {
        this.works = works;
    }

    public Fundings getFundings() {
        if (fundings == null)
            fundings = new Fundings();
        return fundings;
    }

    public void setFundings(Fundings fundings) {
        this.fundings = fundings;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((educations == null) ? 0 : educations.hashCode());
        result = prime * result + ((employments == null) ? 0 : employments.hashCode());
        result = prime * result + ((fundings == null) ? 0 : fundings.hashCode());
        result = prime * result + ((peerReviews == null) ? 0 : peerReviews.hashCode());
        result = prime * result + ((works == null) ? 0 : works.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActivitiesSummary other = (ActivitiesSummary) obj;
        if (educations == null) {
            if (other.educations != null)
                return false;
        } else if (!educations.equals(other.educations))
            return false;
        if (employments == null) {
            if (other.employments != null)
                return false;
        } else if (!employments.equals(other.employments))
            return false;
        if (fundings == null) {
            if (other.fundings != null)
                return false;
        } else if (!fundings.equals(other.fundings))
            return false;                        
        if (peerReviews == null) {
            if (other.peerReviews != null)
                return false;
        } else if (!peerReviews.equals(other.peerReviews))
            return false;                        
        if (works == null) {
            if (other.works != null)
                return false;
        } else if (!works.equals(other.works))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return RecordUtil.convertToString(this);
    }

    @Override
    public Map<Long, ? extends Activity> retrieveActivitiesAsMap() {
        Map<Long, Activity> activities = new HashMap<>();
        // Set works
        if (works != null) {
            List<WorkGroup> groups = works.getWorkGroup();
            for (WorkGroup work : groups) {
                if (work != null) {
                    List<WorkSummary> summaries = work.getWorkSummary();
                    for (WorkSummary summary : summaries) {
                        activities.put(summary.getPutCode(), summary);
                    }
                }
            }
        }
        
        //Set peer reviews
        if(peerReviews != null){
            List<PeerReviewGroup> groups = peerReviews.getPeerReviewGroup();
            for(PeerReviewGroup peerReview : groups) {
                if(peerReview != null) {
                    List<PeerReviewSummary> summaries = peerReview.getPeerReviewSummary();
                    for(PeerReviewSummary summary : summaries) {
                        activities.put(summary.getPutCode(), summary);
                    }
                }
            }
        }
        
        // Set funding
        if (fundings != null) {
            List<FundingGroup> groups = fundings.getFundingGroup();
            for (FundingGroup funding : groups) {
                if (funding != null) {
                    List<FundingSummary> fundings = funding.getFundingSummary();
                    for (FundingSummary summary : fundings) {
                        activities.put(summary.getPutCode(), summary);
                    }
                }
            }
        }
        // Set education
        if (educations != null) {
            for (EducationSummary education : educations.getSummaries()) {
                activities.put(education.getPutCode(), education);
            }
        }
        // Set employment
        if (employments != null) {
            for (EmploymentSummary employment : employments.getSummaries()) {
                activities.put(employment.getPutCode(), employment);
            }
        }
        return activities;
    }

    @Override
    public List<? extends Activity> retrieveActivities() {
        List<Activity> activities = new ArrayList<Activity>();

        return activities;
    }
}
