import pymysql
import redis


def saveJokesToDatabase(jokes):
    """
    将爬取的数据放入MySQL数据库
    :param jokes:
    :return:
    """
    try:
        print('total:%d' % len(jokes))
        connection = pymysql.Connect(host="localhost", user='root', password='root', database='wechat', charset='utf8')
        connection.autocommit(True)
        with connection.cursor() as cursor:

            # 保存之前先过滤重复()
            filter_sql = "SELECT joke_id from jokes WHERE create_time > date_sub(current_timestamp(),INTERVAL 7 day) "

            cursor.execute(filter_sql)
            ids = []
            for row in cursor.fetchall():
                ids.append(row[0])

            jokes = [joke for joke in jokes if int(joke[0]) not in ids]
            sql = "INSERT INTO jokes(joke_id,joke_content) VALUES (%s,%s)"

            cursor.executemany(sql, jokes)

    except:
        import traceback
        traceback.print_exc()
    finally:
        cursor.close()
        connection.close()


def saveJokesToRedis(jokes):
    """
    将爬取的笑话数据放入redis
    :param jokes:
    :return:
    """
    client = redis.Redis(host='localhost', port=6379)
    pip = client.pipeline()
    for joke in jokes:
        # 每个笑话有效期为7天
        res = pip.setnx(('joke_%d' % joke[0]), joke[1])
        pip.expire(('joke_%d' % joke[0]), 60 * 60 * 24 * 7)
    pip.execute()