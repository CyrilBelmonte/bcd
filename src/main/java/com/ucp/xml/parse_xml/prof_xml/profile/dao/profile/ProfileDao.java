package com.ucp.xml.parse_xml.prof_xml.profile.dao.profile;

import java.util.List;

public interface ProfileDao {
    List<Profile> findAllProfile(Float precision);
}
