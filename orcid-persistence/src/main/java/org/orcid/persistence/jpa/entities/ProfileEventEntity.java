/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2013 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.persistence.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Will Simpson
 * 
 */
@Entity
@Table(name = "profile_event")
public class ProfileEventEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String orcid;
    private ProfileEventType type;

    public ProfileEventEntity() {
    }

    public ProfileEventEntity(String orcid, ProfileEventType type) {
        this.orcid = orcid;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "profile_event_seq")
    @SequenceGenerator(name = "profile_event_seq", sequenceName = "profile_event_seq")
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "profile_event_type")
    public ProfileEventType getType() {
        return type;
    }

    public void setType(ProfileEventType type) {
        this.type = type;
    }

}
