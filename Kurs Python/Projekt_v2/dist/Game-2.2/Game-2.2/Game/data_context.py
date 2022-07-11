from typing import Optional

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, ForeignKey, String, create_engine, DateTime, func, Text
from sqlalchemy.orm import relationship, sessionmaker, validates

Base = declarative_base()


class Levels(Base):
    """
    Class representation of db table.
    """
    __tablename__ = 'Levels'
    id = Column(Integer, primary_key=True)
    level_layout = Column(Text, nullable=False)
    best_stars = Column(Integer, default=0)
    best_time = Column(Integer, default=0)
    best_score = Column(Integer, default=0)
    background = Column(Text, default="Game/Assets/Background_1.png")


class DataContext:
    """
    Simple class representing db access object.
    """
    engine = create_engine('sqlite:///Game/Levels.db', echo=False)
    Session = sessionmaker(bind=engine)

    def __init__(self):
        self.session = DataContext.Session()

    def get(self, ID: int) -> Optional[Levels]:
        """
        Retrieves from the database Levels object with specified ID number.
        :param ID: ID number.
        :return: Levels object or None
        """
        ans = self.session.query(Levels).filter(Levels.id == ID).all()
        if len(ans) <= 0:
            return None

        return ans[0]

    def update(self, ID: int, stars: int, time: int, score: int) -> None:
        """
        Updates best level data.
        :param ID: Level ID
        :param stars: Amount of stars collected
        :param time: time in seconds
        :param score: score
        :return: None
        """
        ans = self.session.query(Levels).filter(Levels.id == ID).all()
        if len(ans) <= 0:
            return

        if ans[0].best_score < score or ans[0].best_score == 0:
            ans[0].best_score = score
            ans[0].best_time = time
        if ans[0].best_stars < stars:
            ans[0].best_stars = stars

        self.session.commit()

    def close(self) -> None:
        """
        Closes the connection.
        :return: None
        """
        self.session.close()

    def count_levels(self) -> int:
        """
        Calculates amount of levels present in db.
        :return: Amount of elements in db
        :rtype: int
        """
        ans = self.session.query(Levels).all()
        return len(ans)
