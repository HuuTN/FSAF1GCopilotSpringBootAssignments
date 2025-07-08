def fetch_user_data(user_id):
    """
    Fetch user data from the API.
    
    Args:
        user_id (str): The ID of the user to fetch data for.
        
    Returns:
        dict: The user data if found, otherwise None.
    """
    import requests
    
    url = f"https://api.example.com/users/{user_id}"
    response = requests.get(url)
    
    if response.status_code == 200:
        return response.json()
    else:
        return None