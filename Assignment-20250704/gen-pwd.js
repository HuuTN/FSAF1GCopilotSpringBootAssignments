function generatePassword(length = 12) {
    const upper = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const lower = 'abcdefghijklmnopqrstuvwxyz';
    const digits = '0123456789';
    const specials = '!@#$%^&*()_+-=[]{}|;:,.<>?';
    const all = upper + lower + digits + specials;

    if (length < 4) {
        throw new Error('Password length should be at least 4');
    }

    // Ensure at least one character from each set
    let pwd = [
        upper[Math.floor(Math.random() * upper.length)],
        lower[Math.floor(Math.random() * lower.length)],
        digits[Math.floor(Math.random() * digits.length)],
        specials[Math.floor(Math.random() * specials.length)]
    ];

    for (let i = 4; i < length; i++) {
        pwd.push(all[Math.floor(Math.random() * all.length)]);
    }

    // Shuffle the result
    for (let i = pwd.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [pwd[i], pwd[j]] = [pwd[j], pwd[i]];
    }

    return pwd.join('');
}

// Example usage:
const length = process.argv[2] ? parseInt(process.argv[2], 10) : 12;
console.log(generatePassword(length));