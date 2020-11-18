package com.xzq.module_base.bean;

public class ApplicationCenterDto {


    /**
     * id : 1324655990076882945
     * createTime : null
     * updateTime : null
     * clientId : testApp
     * clientName : 测试app
     * image : http://192.168.101.111:10000/api-file/downloadFile-anon?path=/opt/file/2020-10-28/d2c64cf327d2491aa0519a0b2d65e439.jpg
     * hidden : 1
     * resourceIds :
     * clientSecret : $2a$10$GINoMS1KZSUplKoM9hk7AeQZRHJflfjVbFgstqs80wRaiiwVebGmG
     * clientSecretStr : testApp
     * scope : all
     * authorizedGrantTypes : authorization_code,password,refresh_token,client_credentials
     * webServerRedirectUri : null
     * authorities :
     * accessTokenValiditySeconds : 18000
     * refreshTokenValiditySeconds : 28800
     * additionalInformation : {}
     * autoapprove : true
     * category : 1
     */

    private String id;
    private Object createTime;
    private Object updateTime;
    private String clientId;
    private String clientName;
    private String image;
    private int hidden;
    private String resourceIds;
    private String clientSecret;
    private String clientSecretStr;
    private String scope;
    private String authorizedGrantTypes;
    private Object webServerRedirectUri;
    private String authorities;
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;
    private String additionalInformation;
    private String autoapprove;
    private int category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecretStr() {
        return clientSecretStr;
    }

    public void setClientSecretStr(String clientSecretStr) {
        this.clientSecretStr = clientSecretStr;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Object getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(Object webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public int getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
