package lv.ozo.photoAppApiUsers.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository <UserEntity, Long> {
}
