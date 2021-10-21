#### ORM 이란?

Class -> db의 Table 로 매핑하는 것

##### build.gradle:app

```
plugins{ id 'kotlin-kapt'}
dependencies { kapt 'androidx.room:room-compiler:2.3.0'}
```

kapt란?

 kotlin 에서도 어노테이션을 사용가능하게 해줌



RoomMemo   ::: 컬럼명과 변수가 명세된 객체

```
@Entity(tableName = "orm_memo")
class RoomMemo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo
    var datetime: Long = 0

    constructor(content: String, datetime: Long) {
        this.content = content
        this.datetime = datetime
    }
}
```

RoomMemoDAO   ::: 직접적인 구현이 아니라 RoomHelper 를 통한 추상화적인 구현에 사용됨

```
@Dao
interface RoomMemoDAO {
    @Query("select * from orm_memo")
    fun getAll(): List<RoomMemo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}
```

RoomHelper    ::: Room.databaseBuilder 를 통한 데이터베이스 생성하게 해줌

```
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDAO
}
```

  스프링 처럼 인터페이스를 통한 추상화를 통해 간단하게 helper 를 교체할 수 있다.







