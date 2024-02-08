package vieira.pedro.gerenciadorsenhas.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

    private String title;

    private String details;

    private Integer status;

    private List<FieldError> fields;

    private LocalDateTime timestamp;

    public ErrorDetails() {}

    public ErrorDetails(String title, String details, Integer status, List<FieldError> fields, LocalDateTime timestamp) {
        this.title = title;
        this.details = details;
        this.status = status;
        this.fields = fields;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<FieldError> getFields() {
        return fields;
    }

    public void setFields(List<FieldError> fields) {
        this.fields = fields;
    }

    static class FieldError{
        private String name;
        private String details;

        public FieldError(String name, String details) {
            this.name = name;
            this.details = details;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}
