#Using API to fetch user data
def fetch_user_data(api_url, user_id):
    import requests
    
    try:
        response = requests.get(f"{api_url}/users/{user_id}")
        response.raise_for_status()  # Raise an error for bad responses
        user_data = response.json()
        return user_data
    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")
        return None