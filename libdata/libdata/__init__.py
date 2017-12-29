import pymysql

from libdata import settings


def get_conn():
    return pymysql.connect(host=settings.MYSQL_HOST,
                           port=settings.MYSQL_PORT,
                           db=settings.MYSQL_DB,
                           user=settings.MYSQL_USER,
                           passwd=settings.MYSQL_PASSWORD,
                           charset='utf8',
                           cursorclass=pymysql.cursors.DictCursor,
                           use_unicode=True)
