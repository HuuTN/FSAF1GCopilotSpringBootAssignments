#Use APi to fetch user data

def fetch_user_data(user_id):
    import requests

    url = f"https://jsonplaceholder.typicode.com/users/{user_id}"
    response = requests.get(url)

    if response.status_code == 200:
        return response.json()
    else:
        return None