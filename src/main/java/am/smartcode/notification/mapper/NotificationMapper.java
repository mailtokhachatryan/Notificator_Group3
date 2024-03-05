package am.smartcode.notification.mapper;

import am.smartcode.notification.model.dto.CreateNotificationDto;
import am.smartcode.notification.model.dto.NotificationDto;
import am.smartcode.notification.model.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationEntity toEntity(CreateNotificationDto createNotificationDto);

    @Mapping(target = "notificationDate", source = "notificationDateTime", qualifiedByName = "millisToLocalDateTime")
    @Mapping(target = "creationDate", source = "creationDateTime", qualifiedByName = "millisToLocalDateTime")
    NotificationDto toDto(NotificationEntity notification);


    @Named("millisToLocalDateTime")
    default LocalDateTime convertMillisToLocalDateTime(Long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.of("UTC")).toLocalDateTime();
    }


}
