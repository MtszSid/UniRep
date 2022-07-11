using System.Data.Entity;

namespace Core.DB
{
    public class DaneStudentowDataContext: DbContext
    {
        public DaneStudentowDataContext(string conectionString): base(conectionString) { }
        public virtual DbSet<Student> Students { get; set; }
        public virtual DbSet<Miejscowosc> Miejscowoscs { get; set; }
    }
}
