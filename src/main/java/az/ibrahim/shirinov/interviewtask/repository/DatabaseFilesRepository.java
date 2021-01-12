package az.ibrahim.shirinov.interviewtask.repository;

import az.ibrahim.shirinov.interviewtask.entity.DatabaseFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFilesRepository extends JpaRepository<DatabaseFiles,Long> {

}
