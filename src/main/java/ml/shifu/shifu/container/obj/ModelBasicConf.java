/*
 * Copyright [2012-2014] PayPal Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ml.shifu.shifu.container.obj;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ml.shifu.shifu.util.Constants;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelBasicConf class is config part for basic part in ModelConfig.json.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelBasicConf {

    @JsonDeserialize(using = RunModeDeserializer.class)
    public static enum RunMode {
        LOCAL, DIST, MAPRED // MAPRED is the same as DIST while DIST is better to explain this mode as 'distributed'
                            // mode.
    }

    /**
     * Model set name, which is used to differentiate each data set modeling in Shifu. Please be careful if you have two
     * model set running with the same dataset. this case will be conflict in model running.
     */
    private String name;

    /**
     * Generated based on current user.
     */
    private String author;

    /**
     * What this model is, user can specified the description.
     */
    private String description;

    /**
     * Version in ModelConfig.json
     */
    private String version = Constants.version;

    /**
     * Run in local or Hadoop Cluster
     */
    private RunMode runMode = RunMode.LOCAL;

    /**
     * If enable post training step
     */
    private Boolean postTrainOn = Boolean.FALSE;

    /**
     * Customized paths like default model set path
     */
    private Map<String, String> customPaths;

    public ModelBasicConf() {
        customPaths = new HashMap<String, String>(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RunMode getRunMode() {
        return runMode;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    public Map<String, String> getCustomPaths() {
        return customPaths;
    }

    public void setCustomPaths(Map<String, String> customPaths) {
        this.customPaths = customPaths;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the postTrainOn
     */
    public Boolean getPostTrainOn() {
        return postTrainOn;
    }

    /**
     * @param postTrainOn
     *            the postTrainOn to set
     */
    public void setPostTrainOn(Boolean postTrainOn) {
        this.postTrainOn = postTrainOn;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof ModelBasicConf)) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        ModelBasicConf basic = (ModelBasicConf) obj;
        return StringUtils.equals(basic.getName(), name) && StringUtils.equals(basic.getAuthor(), author)
                && StringUtils.equals(basic.getDescription(), description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public ModelBasicConf clone() {
        ModelBasicConf other = new ModelBasicConf();
        other.setName(name);
        other.setAuthor(author);
        other.setDescription(description);
        other.setRunMode(runMode);
        other.setVersion(version);
        other.setPostTrainOn(postTrainOn);
        if(customPaths != null) {
            other.setCustomPaths(new HashMap<String, String>(customPaths));
        }
        return other;
    }

}
