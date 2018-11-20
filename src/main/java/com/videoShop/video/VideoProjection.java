package com.videoShop.video;

import org.springframework.data.rest.core.config.Projection;

import com.videoShop.common.domains.Language;

@Projection(name = "video", types = { Video.class })
public interface VideoProjection {

    public Long getId();
    public String getTitle();
    public Integer getRating();
    public Integer getHelpful();
    public Integer getHelpless();
    public Language getLanguage();
    public Boolean getEnabled();
    public String getVideoLink();
}
