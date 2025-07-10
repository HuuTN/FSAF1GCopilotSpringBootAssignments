# Use API to fetch user data
def fetch_user_data(user_id):
    """
    Fetch user data from the API based on user_id.
    
    Args:
        user_id (str): The ID of the user to fetch data for.
        
    Returns:
        dict: A dictionary containing user data.
    """
    import requests

    url = f"https://api.example.com/users/{user_id}"
    response = requests.get(url)

    if response.status_code == 200:
        return response.json()
    else:
        response.raise_for_status()