package com.projet.goodmood.repository;

import com.projet.goodmood.models.Planning;
import com.projet.goodmood.models.Tache;
import com.projet.goodmood.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningRepo extends JpaRepository<Planning, Long> {

    @Query(value = "select * from planning where users=:users ", nativeQuery = true)
    public List<Planning>  AfficherPlanningDunUser(Long users);

    //Planning findByNomplanning(String nomplanning);
    List<Planning> findByNomplanning(String nomplanning);
    //Planning findByNomplanningAndUsers(String nomplanning, Long users);

    boolean existsByNomplanningAndUsers(String nomplanning, Users users);


    public long getPlanningEndTime(Long planningId) {
        String selectQuery = "SELECT " + KEY_END_TIME + " FROM " + TABLE_PLANNING + " WHERE " + KEY_ID + " = " + planningId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        long planningEndTime = 0;
        if (cursor.moveToFirst()) {
            planningEndTime = cursor.getLong(cursor.getColumnIndex(KEY_END_TIME));
        }

        cursor.close();
        db.close();
        return planningEndTime;
    }


}
