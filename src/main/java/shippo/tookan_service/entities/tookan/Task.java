package shippo.tookan_service.entities.tookan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.json.JSONArray;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private String order_id;
    private Integer team_id;
    private String job_id;
    private Integer auto_assignment;
    private String job_description;
    private String job_pickup_phone;
    // Optional
    private String job_pickup_name;
    // Optional
    private String job_pickup_email;
    private String job_pickup_address;
    // Optional
    private String job_pickup_latitude;
    // Optional
    private String job_pickup_longitude;
    private String job_pickup_datetime;
    private String pickup_custom_field_template;
    private JSONArray pickup_meta_data;
    // Optional
    private String customer_email;
    // Optional
    private String customer_username;
    private String customer_phone;
    private String customer_address;
    // Optional
    private String latitude;
    // Optional
    private String longitude;
    private String job_delivery_datetime;
    private String custom_field_template;
    private JSONArray meta_data;
    private Integer timezone;
    // Fixed = 1 for pickup and delivery task
    private Integer has_pickup;
    // Fixed = 1 for pickup and delivery task
    private Integer has_delivery;
    // Fixed = 0 for pickup and delivery task
    private Integer layout_type;
    private Integer tracking_link;
    private String fleet_id;
    private Integer notify;
    private Integer geofence;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getAuto_assignment() {
        return auto_assignment;
    }

    public void setAuto_assignment(Integer auto_assignment) {
        this.auto_assignment = auto_assignment;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_pickup_phone() {
        return job_pickup_phone;
    }

    public void setJob_pickup_phone(String job_pickup_phone) {
        this.job_pickup_phone = job_pickup_phone;
    }

    public String getJob_pickup_name() {
        return job_pickup_name;
    }

    public void setJob_pickup_name(String job_pickup_name) {
        this.job_pickup_name = job_pickup_name;
    }

    public String getJob_pickup_email() {
        return job_pickup_email;
    }

    public void setJob_pickup_email(String job_pickup_email) {
        this.job_pickup_email = job_pickup_email;
    }

    public String getJob_pickup_address() {
        return job_pickup_address;
    }

    public void setJob_pickup_address(String job_pickup_address) {
        this.job_pickup_address = job_pickup_address;
    }

    public String getJob_pickup_latitude() {
        return job_pickup_latitude;
    }

    public void setJob_pickup_latitude(String job_pickup_latitude) {
        this.job_pickup_latitude = job_pickup_latitude;
    }

    public String getJob_pickup_longitude() {
        return job_pickup_longitude;
    }

    public void setJob_pickup_longitude(String job_pickup_longitude) {
        this.job_pickup_longitude = job_pickup_longitude;
    }

    public String getJob_pickup_datetime() {
        return job_pickup_datetime;
    }

    public void setJob_pickup_datetime(String job_pickup_datetime) {
        this.job_pickup_datetime = job_pickup_datetime;
    }

    public String getPickup_custom_field_template() {
        return pickup_custom_field_template;
    }

    public void setPickup_custom_field_template(String pickup_custom_field_template) {
        this.pickup_custom_field_template = pickup_custom_field_template;
    }

    public JSONArray getPickup_meta_data() {
        return pickup_meta_data;
    }

    public void setPickup_meta_data(JSONArray pickup_meta_data) {
        this.pickup_meta_data = pickup_meta_data;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getJob_delivery_datetime() {
        return job_delivery_datetime;
    }

    public void setJob_delivery_datetime(String job_delivery_datetime) {
        this.job_delivery_datetime = job_delivery_datetime;
    }

    public String getCustom_field_template() {
        return custom_field_template;
    }

    public void setCustom_field_template(String custom_field_template) {
        this.custom_field_template = custom_field_template;
    }

    public JSONArray getMeta_data() {
        return meta_data;
    }

    public void setMeta_data(JSONArray meta_data) {
        this.meta_data = meta_data;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getHas_pickup() {
        return has_pickup;
    }

    public void setHas_pickup(Integer has_pickup) {
        this.has_pickup = has_pickup;
    }

    public Integer getHas_delivery() {
        return has_delivery;
    }

    public void setHas_delivery(Integer has_delivery) {
        this.has_delivery = has_delivery;
    }

    public Integer getLayout_type() {
        return layout_type;
    }

    public void setLayout_type(Integer layout_type) {
        this.layout_type = layout_type;
    }

    public Integer getTracking_link() {
        return tracking_link;
    }

    public void setTracking_link(Integer tracking_link) {
        this.tracking_link = tracking_link;
    }

    public String getFleet_id() {
        return fleet_id;
    }

    public void setFleet_id(String fleet_id) {
        this.fleet_id = fleet_id;
    }

    public Integer getNotify() {
        return notify;
    }

    public void setNotify(Integer notify) {
        this.notify = notify;
    }

    public Integer getGeofence() {
        return geofence;
    }

    public void setGeofence(Integer geofence) {
        this.geofence = geofence;
    }
}
