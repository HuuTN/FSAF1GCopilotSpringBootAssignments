#use Api to fetch data from a URL : examnple.com/api/userd):
import requests
def fetch_user_data(user_id):
    url = f"https://example.com/api/users/{user_id}"
    try:
        response = requests.get(url)
        response.raise_for_status()  # Raise an error for bad responses
        user_data = response.json()  # Parse JSON response
        return user_data
    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")
        return None